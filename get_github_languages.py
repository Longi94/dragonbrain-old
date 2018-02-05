import json
import requests
import sys
import yaml
from collections import OrderedDict


def ordered_load(stream, Loader=yaml.Loader, object_pairs_hook=OrderedDict):
    """
    Parse the first YAML document in a stream
    and produce the corresponding Python Orderered Dictionary.
    """

    class OrderedLoader(Loader):
        pass

    OrderedLoader.add_constructor(
        yaml.resolver.BaseResolver.DEFAULT_MAPPING_TAG,
        lambda loader, node: object_pairs_hook(loader.construct_pairs(node)))

    return yaml.load(stream, OrderedLoader)


def order_by_keys(dict):
    """
    Sort a dictionary by keys, case insensitive ie [ Ada, eC, Fortran ]
    Default ordering, or using json.dump with sort_keys=True, produces
    [ Ada, Fortran, eC ]
    """
    from collections import OrderedDict
    return OrderedDict(sorted(dict.items(), key=lambda s: s[0].lower()))


def get_file(url):
    """
    Return the URL body, or False if page not found

    Keyword arguments:
    url -- url to parse
    """
    try:
        r = requests.get(url)
    except:
        sys.exit("Request fatal error :  %s" % sys.exc_info()[1])

    if r.status_code != 200:
        return False

    return r.text


def run():
    # Get list of all langs
    print("Getting list of languages ...")
    yml = get_file("https://raw.githubusercontent.com/github/linguist/master/"
                   "lib/linguist/languages.yml")
    langs_yml = ordered_load(yml)
    langs_yml = order_by_keys(langs_yml)

    # List construction done, count keys
    lang_count = len(langs_yml)
    print("Found %d languages" % lang_count)

    # Construct the wanted list
    langs = OrderedDict()
    for lang in langs_yml.keys():
        if ("type" not in langs_yml[lang] or
                "color" in langs_yml[lang] or
                langs_yml[lang]["type"] == "programming"):
            print("   Parsing the color for '%s' ..." % (lang))
            if "color" in langs_yml[lang]:
                langs[lang] = langs_yml[lang]["color"]
    print("Writing a new JSON file ...")
    write_json(langs)
    print("All done!")


def write_json(text, filename='colors.json'):
    """
    Write a JSON file from a dictionary
    """
    with open(filename, 'w') as f:
        f.write(json.dumps(text, indent=4) + '\n')


# #
# now do stuff
# #
run()

import os, sys
from PIL import Image

size = 200, 200

for infile in sys.argv[1:]:
    outfile = os.path.splitext(infile)[0] + "_thumbnail.jpg"
    if infile != outfile:
        try:
            im = Image.open(infile)

            ratio = max(size[0] / im.size[0], size[1] / im.size[1])

            new_size = im.size[0] * ratio, im.size[1] * ratio

            im.thumbnail(new_size, Image.ANTIALIAS)
            im.save(outfile, "JPEG")
        except IOError:
            print("cannot create thumbnail for '%s'" % infile)

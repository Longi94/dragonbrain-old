$('document').ready(function () {

    document.getElementById('greeting-form').addEventListener('submit', function (evt) {
        evt.preventDefault();
        var firstname = evt.target.elements.firstname.value;
        var lastname = evt.target.elements.lastname.value;
        var greeting = 'Hello';
        if (firstname || lastname) {
            greeting += ', ';
            if (firstname && lastname) {
                greeting += firstname + ' ' + lastname;
            } else if (lastname) {
                greeting += 'Mx. ' + lastname;
            } else {
                greeting += firstname;
            }
        }
        greeting += '!';

        document.getElementById('greeting').textContent = greeting;
    });
});

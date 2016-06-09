function startTabuada() {
    var x;
    if (confirm("Press a button!") == true) {
        x = "You pressed OK!";
    } else {
        x = "You pressed Cancel!";
    }
    document.getElementById("demo").innerHTML = x;
}

function focusInput() {
    document.getElementById('j_idt31:tabuadaForm:answer').focus();
}



function login() {
    if($('#mail').val() == '' || $('#password').val() == '') {
        Swal.fire({
            icon: 'warning',
            title: 'Attenzione!',
            text: 'Compila i campi richiesti per proseguire',
            customClass: {
                confirmButton: 'btn btn-primary'
            }
        })
    } else {
        $.ajax('/login', {
            type: 'POST',
            data: 'data='+JSON.stringify({
                mail: $('#mail').val(),
                password: $('#password').val()
            }),
            success: (risposta) => {
                if(risposta.esito) {
                    console.log('logged')
                } else {
                    console.log(risposta.message)
                    if(risposta.message == 'NOTAUTH') {
                        Swal.fire({
                            icon: 'warning',
                            title: 'Controlla i dati',
                            text: 'Controlla i dati che hai inserito! Potrebbero essere errati',
                            customClass: {
                                confirmButton: 'btn btn-primary'
                            }
                        })
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Errore!',
                            text: 'Si è verificato un errore interno al server',
                            customClass: {
                                confirmButton: 'btn btn-primary'
                            }
                        })
                    }
                }
            },
            error: () => {
                Swal.fire({
                    icon: 'error',
                    title: 'Errore!',
                    text: 'Si è verificato un errore di comunicazione con il server',
                    customClass: {
                        confirmButton: 'btn btn-primary'
                    }
                })
            }
        })
    }
}

function isAlreadyLogged() {
    $.ajax('/getSessione', {
        type: 'POST',
        success: (risposta) => {
            if(risposta.esito) {
                // l'utente è già autenticato
                console.log('utente già autenticato')
            }
        }
    })
}

isAlreadyLogged()
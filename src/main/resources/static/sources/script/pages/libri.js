// script che gestisce la pagina con la tabella dei libri

class Libri {

    constructor(limit=15, offset=0) {
        this.limit = limit
        this.offset = offset
        this.popolaTabella()
    }

    popolaTabella() {
        $.ajax('/getLibri', {
            type: 'POST',
            data: 'data='+JSON.stringify({
                limit: this.limit,
                offet: this.offset
            }),
            success: (risposta) => {
                if(risposta.esito) {
                    // abbiamo correttamente recuperato i libri
                } else {
                    console.log(risposta.message)
                    this.messaggioErrore()
                }
            },
            error: () => {
                this.messaggioErrore()
            }
        })
    }

    messaggioErrore() {
        Swal.fire({
            icon: 'error',
            title: 'Errore!',
            text: 'Si è verificato un errore nel recupero dei dati!',
            customClass: {
                confirmButton: 'btn btn-primary'
            }
        }).then(() => {
            location.href = '/'
        })
    }

}

frame = new Libri()
// script che gestisce la pagina con la tabella dei libri

class Libri {

    constructor(limit=15, offset=0) {
        this.limit = limit
        this.offset = offset
        $('#numElementi').attr('value', this.limit)
        $('#offset').attr('value', this.offset)

        $('#frecciaAvanti').click(() => {
            this.offset += this.limit
            this.limit *= 2

            if(this.limit <= 0)
                this.limit = 0

            if(this.offset <= 0)
                this.offset = 0

            if(this.limit - this.offset > 50)
                this.limit = 50

            $('#numElementi').attr('value', this.limit)
            $('#offset').attr('value', this.offset)

            this.popolaTabella()
        })

        $('#frecciaIndietro').click(() => {
            this.limit /= 2
            this.offset -= this.limit

            if(this.limit <= 0)
                this.limit = 0

            if(this.offset <= 0)
                this.offset = 0

            if(this.limit - this.offset > 50)
                this.limit = 50

            $('#numElementi').attr('value', this.limit)
            $('#offset').attr('value', this.offset)

            this.popolaTabella()
        })

        this.popolaTabella()
    }

    popolaTabella() {
        $('#tabellaLibri').empty()

        $.ajax('/getLibri', {
            type: 'POST',
            data: 'data='+JSON.stringify({
                limit: this.limit,
                offset: this.offset
            }),
            success: (risposta) => {
                if(risposta.esito) {
                    // abbiamo correttamente recuperato i libri
                    risposta.data.sort((a,b) =>
                        a.titolo.localeCompare(b.titolo)
                    ).forEach((libro) => {
                        $('#tabellaLibri').append(
                            '<tr>'+
                                '<td>'+libro.titolo+'</td>'+
                                '<td>'+libro.nome+' '+libro.cognome+'</td>'+
                                '<td>'+libro.annoPubblicazione+'</td>'+
                                '<td><a class="link link-primary" onclick="frame.schedaLibro('+libro.ID+')">scheda</a></td>'+
                            '</tr>'
                        )
                    })
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
            //location.href = '/'
        })
    }

    schedaLibro(ID) {
        Swal.fire("Hello")
    }

}

frame = new Libri()
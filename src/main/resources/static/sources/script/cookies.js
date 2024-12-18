class CookieManager {

    constructor(scadenza) {
        const d = new Date()
        d.setTime(d.getTime() + (scadenza*24*60*60*1000))
        this.scadenza = "expires="+ d.toUTCString()
    }

    set(nome, valore) {
        document.cookie = nome + "=" + valore + ";" + this.scadenza + ";path=/; SameSite=strict;"
    }

    get(nome) {
        let name = nome + "="
        let decodedCookie = decodeURIComponent(document.cookie)
        let ca = decodedCookie.split(';')

        for(let i = 0; i <ca.length; i++) {
          let c = ca[i];

          while(c.charAt(0) == ' ')
            c = c.substring(1)

          if(c.indexOf(name) == 0)
            return c.substring(name.length, c.length)
        }

        return ""
    }

    delete(nome) {
        let a = new CookieManager(-1)
        a.set(nome,'')
    }
}

function cambiaLingua() {
    let a = new CookieManager(3)

    if(a.get('language') == 'it')
        a.set('language','en')
    else
        a.set('language','it')

    window.location.reload()
}
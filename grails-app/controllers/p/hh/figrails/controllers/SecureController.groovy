package p.hh.figrails.controllers

import grails.plugin.springsecurity.annotation.Secured

class SecureController {

    def index() {
        render 'Secure access only'
    }
}

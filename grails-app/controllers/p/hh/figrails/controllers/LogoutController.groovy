package p.hh.figrails.controllers

import grails.plugin.springsecurity.SpringSecurityUtils

class LogoutController {

    def index() {
        redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
    }
}

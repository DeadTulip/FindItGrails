package p.hh.figrails.controllers

import grails.plugin.springsecurity.annotation.Secured
import p.hh.figrails.domain.Team

class UserController {

    def index() {
        def teams = []
        teams << new Team(teamName: 'teamA')
        teams << new Team(teamName: 'teamB')
        render(view: 'index', model: [teams: teams])
    }
}

package p.hh.figrails.controllers

import p.hh.figrails.domain.User

class TeamController {

    def index() {
        def members = []
        members << new User(userName: 'haihan1')
        members << new User(userName: 'haihan2')
        members << new User(userName: 'haihan3')
        render(view: 'index', model: [teamName: 'teamName', creator: 'me', members: members])
    }
}

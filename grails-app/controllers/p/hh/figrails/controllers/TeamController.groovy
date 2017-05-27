package p.hh.figrails.controllers

import p.hh.figrails.commands.TeamCommand
import p.hh.figrails.domain.Team
import p.hh.figrails.domain.User

class TeamController {
    def teamService

    def index() {

    }

    def open() {
        Long teamId = params.long("teamId")
        Team team = teamService.findTeamById(teamId)
        TeamCommand cmd = teamService.mapTeamToTeamCommand(team)
        render(view: 'index', model: [cmd: cmd])
    }
}

package p.hh.figrails.controllers

import p.hh.figrails.domain.Team

class UserController {
    def teamService
    def springSecurityService

    def index() {
        Set<Team> ownedTeams = teamService.teamsCreatedByUser(springSecurityService.currentUser)
        Set<Team> joinedTeams = teamService.teamsJoinedByUser(springSecurityService.currentUser)
        render(view: 'index', model: [ownedTeams: ownedTeams, joinedTeams: joinedTeams, message: flash.message])
    }

    def createTeam() {
        String teamName = params.teamName
        Team team = teamService.findTeam(teamName)
        if (team) {
            flash.message = "Team with name \"${teamName}\" already exist."
        } else {
            teamService.createTeam(teamName, springSecurityService.currentUser)
        }
        redirect(controller: 'user', action: 'index')
    }

}

package p.hh.figrails.controllers

import grails.plugin.springsecurity.annotation.Secured
import p.hh.figrails.domain.Team

class UserController {
    def teamService
    def springSecurityService

    def index() {
        List<Team> ownedTeams = teamService.teamsOwnedByUser(springSecurityService.currentUser)
        List<Team> joinedTeams = teamService.teamsJoinedByUser(springSecurityService.currentUser)
        render(view: 'index', model: [ownedTeams: ownedTeams, joinedTeams: joinedTeams])
    }

    def createTeam() {
        String createTeamError = null
        String teamName = params.teamName
        Team team = teamService.findTeamWithName(teamName)
        if (team) {
            createTeamError = "Team with name \"${teamName}\" already exist."
        } else {
            teamService.createTeam(teamName, springSecurityService.currentUser)
        }

        List<Team> ownedTeams = teamService.teamsOwnedByUser(springSecurityService.currentUser)
        List<Team> joinedTeams = teamService.teamsJoinedByUser(springSecurityService.currentUser)
        render(view: 'index', model: [ownedTeams: ownedTeams, joinedTeams: joinedTeams, createTeamError: createTeamError])
    }
}

package p.hh.figrails.controllers

import grails.plugin.springsecurity.annotation.Secured
import p.hh.figrails.domain.Team

class UserController {
    def teamService
    def springSecurityService

    def index() {
        Set<Team> ownedTeams = teamService.teamsCreatedByUser(springSecurityService.currentUser)
        Set<Team> joinedTeams = teamService.teamsJoinedByUser(springSecurityService.currentUser)
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

        Set<Team> ownedTeams = teamService.teamsCreatedByUser(springSecurityService.currentUser)
        Set<Team> joinedTeams = teamService.teamsJoinedByUser(springSecurityService.currentUser)
        render(view: 'index', model: [ownedTeams: ownedTeams, joinedTeams: joinedTeams, createTeamError: createTeamError])
    }

}

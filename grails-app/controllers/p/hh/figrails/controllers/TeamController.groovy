package p.hh.figrails.controllers

import p.hh.figrails.commands.TeamCommand
import p.hh.figrails.domain.Team
import p.hh.figrails.domain.User

class TeamController {
    def userService
    def teamService
    def springSecurityService

    def index() {

    }

    def open() {
        Long teamId = params.long("teamId")
        Team team = teamService.findTeamById(teamId)
        TeamCommand cmd = teamService.mapTeamToTeamCommand(team)
        render(view: 'index', model: [cmd: cmd])
    }

    def addMember() {
        String memberName = params.memberName
        Team team = teamService.findTeamById(params.long("teamId"))
        User user = userService.findUserByName(memberName)
        teamService.addMemberToTeam(user, team)

        TeamCommand cmd = teamService.mapTeamToTeamCommand(team)
        render(view: 'index', model: [cmd: cmd])
    }

    def leaveTeam() {
        Team team = teamService.findTeamById(params.long("teamId"))
        teamService.removeMemberFromTeam(springSecurityService.currentUser, team)
        redirect(controller: 'user', action: 'index')
    }

    def removeMember() {
        Team team = teamService.findTeamById(params.long("teamId"))
        User user = userService.findUserById(params.long("userId"))
        teamService.removeMemberFromTeam(user, team)
        redirect(controller: 'team', action: 'open', params: [teamId: team.id])
    }
}

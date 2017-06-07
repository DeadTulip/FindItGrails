package p.hh.finditgrails.services

import grails.transaction.Transactional
import p.hh.figrails.commands.TeamCommand
import p.hh.figrails.domain.Team
import p.hh.figrails.domain.User

@Transactional
class TeamService {
    def itemService

    Set<Team> teamsCreatedByUser(User user) {
        Team.findAllByCreator(user)
    }

    Set<Team> teamsAccessibleByUser(User user) {
        user.teams
    }

    Set<Team> teamsJoinedByUser(User user) {
        teamsAccessibleByUser(user) - teamsCreatedByUser(user)
    }

    Team createTeam(String name, User creator) {
        Team team = new Team()
        team.teamName = name
        team.creator = creator
        team.save(failOnError: true)

        creator.addToTeams(team)
        creator.save(failOnError: true)

        team
    }

    Team findTeam(String name) {
        Team.findByTeamName(name)
    }

    Team findTeam(Long id) {
        Team.findById(id)
    }

    TeamCommand mapTeamToCommand(Team team) {
        TeamCommand teamCommand = new TeamCommand()
        teamCommand.team = team
        List<TeamCommand.UserItemCount> userItemCountList = []

        List<User> members = []
        members.addAll(team.members)
        members.each {
            userItemCountList.add(
                    new TeamCommand.UserItemCount(
                            user: it,
                            count: itemService.findAllCreatedItemsByUser(it).size()))
        }
        teamCommand.userItemCountList = userItemCountList
        teamCommand
    }

    void addMemberToTeam(User user, Team team) {
        user.addToTeams(team)
        user.save()
    }

    void removeMemberFromTeam(User user, Team team) {
        user.removeFromTeams(team)
        user.save()
    }
}

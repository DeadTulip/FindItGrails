package p.hh.finditgrails.services

import grails.transaction.Transactional
import org.grails.datastore.mapping.query.Query
import p.hh.figrails.commands.TeamCommand
import p.hh.figrails.domain.Team
import p.hh.figrails.domain.User

@Transactional
class TeamService {
    def itemService

    Set<Team> teamsCreatedByUser(User user) {
        user.createdTeams.toList()
    }

    Set<Team> teamsJoinedByUser(User user) {
        user.teams
    }

    Team findTeamWithName(String name) {
        Team.findByTeamName(name)
    }

    Team createTeam(String name, User creator) {
        Team team = new Team()
        team.teamName = name
        team.creator = creator
        team.save(failOnError: true, flush: true)

        creator.teams.add(team)
        creator.save(failOnError: true, flush: true)

        team
    }

    Team findTeamById(Long id) {
        Team.findById(id)
    }

    TeamCommand mapTeamToTeamCommand(Team team) {
        TeamCommand teamCommand = new TeamCommand()
        teamCommand.team = team
        List<TeamCommand.UserItemCount> userItemCountList = []

        List<User> members = []
        members.add(team.creator)
        members.addAll(team.members)
        members.each {
            userItemCountList.add(
                    new TeamCommand.UserItemCount(
                            user: it,
                            count: itemService.findAllOwnedItemsByUser(it).size()))
        }
        teamCommand.userItemCountList = userItemCountList
        teamCommand
    }

    Team addMemberToTeam(User user, Team team) {
        team.members.add(user)
        team.save(flush: true)
    }

    void removeMemberFromTeam(User user, Team team) {
        team.members.remove(user)
        team.save(flush: true)
    }
}

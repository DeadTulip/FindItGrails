package p.hh.finditgrails.services

import grails.transaction.Transactional
import p.hh.figrails.domain.Team
import p.hh.figrails.domain.User

@Transactional
class TeamService {

    List<Team> teamsOwnedByUser(User user) {
        Team.findAllByCreator(user)
    }

    List<Team> teamsJoinedByUser(User user) {
        List<Team> teams = Team.findAll()
        teams.findAll{
            it.members.contains(user)
        }
    }

    Team findTeamWithName(String name) {
        Team.findByTeamName(name)
    }

    Team createTeam(String name, User creator) {
        Team team = new Team()
        team.teamName = name
        team.creator = creator
        team.save(failOnError: true, flush: true)
    }
}

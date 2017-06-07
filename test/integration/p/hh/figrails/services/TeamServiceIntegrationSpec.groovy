package p.hh.figrails.services

import grails.test.spock.IntegrationSpec
import p.hh.figrails.domain.Team
import p.hh.figrails.domain.User
import p.hh.figrails.util.FigFactory
import p.hh.finditgrails.services.TeamService

class TeamServiceIntegrationSpec extends IntegrationSpec {
    TeamService teamService

    void "test createTeam"() {
        given:
        User user = FigFactory.giveUser()

        when:
        Team team = teamService.createTeam("myTeam", user)

        then:
        team.id != null
        team.creator == user
        Team.findAllByCreator(user).contains(team)
    }

    void "test teams related to a user"() {
        given:
        User user1 = FigFactory.giveUser()
        Team team1 = teamService.createTeam("myTeam1", user1)
        User user2 = FigFactory.giveUser()
        Team team2 = teamService.createTeam("myTeam2", user2)
        team2.addToMembers(user1)

        user1.save(flush: true)

        when:
        Set<Team> createdTeams = teamService.teamsCreatedByUser(user1)
        Set<Team> accessibleTeams = teamService.teamsAccessibleByUser(user1)
        Set<Team> joinedTeams = teamService.teamsJoinedByUser(user1)

        then:
        createdTeams == [team1] as Set
        accessibleTeams.sort { a, b -> a.id <=> b.id } as Set == [team1, team2] as Set
        joinedTeams == [team2] as Set
    }

    void "test find a team"() {
        given:
        Team team = FigFactory.giveTeam(FigFactory.giveUser())

        when:
        Team teamFoundByName = teamService.findTeam(team.teamName)
        Team teamFoundById = teamService.findTeam(team.id)

        then:
        team == teamFoundByName
        team == teamFoundById
    }

    void "test addMemberToTeam and removeMemberToTeam"() {
        given:
        User user1 = FigFactory.giveUser()
        Team team1 = FigFactory.giveTeam(user1)
        User user2 = FigFactory.giveUser()
        User user3 = FigFactory.giveUser()
        teamService.addMemberToTeam(user2, team1)
        teamService.addMemberToTeam(user3, team1)

        expect:
        team1.members.contains(user2)
        team1.members.contains(user3)
        teamService.removeMemberFromTeam(user2, team1)
        !team1.members.contains(user2)

    }

}

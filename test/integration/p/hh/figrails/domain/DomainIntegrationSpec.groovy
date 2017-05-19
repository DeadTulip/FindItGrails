package p.hh.figrails.domain

import grails.test.spock.IntegrationSpec


class DomainIntegrationSpec extends IntegrationSpec {
    def "test User and team"() {
        given:
        User user = new User()
        user.userName = 'haihan'

        Team team = new Team()
        team.creator = user

        team.members.add(user)

        when:
        team.save(flush: true)

        then:
        println user.id
    }


    def "test locations"() {
        given:
        DiskLocation dl = new DiskLocation()
        dl.onDiskName = 'temp_ondiskname'
        dl.save(flush: true)

        PhysicalLocation pl = new PhysicalLocation()
        pl.name = 'temp_name'
        pl.description = 'temp_description'
        pl.save(flush: true)

        when:
        def location = Location.findAll()

        then:
        println location
    }

    def "test user role"() {
        given:
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def userRole = new Role(authority:  'ROLE_USR').save()

        def testUser = new User(username: 'me', password: 'password').save()

        when:
        UserRole.create(testUser, adminRole, true).save()

        then:
        User.count() == 1
        Role.count() == 2
        UserRole.count() == 1
    }
}

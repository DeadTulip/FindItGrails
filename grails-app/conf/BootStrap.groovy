import p.hh.figrails.domain.Role
import p.hh.figrails.domain.User
import p.hh.figrails.domain.UserRole

class BootStrap {

    def init = { servletContext ->
        def testUser = User.findByUsername('me') ?: new User(username: 'me', password: 'password').save(failOnError: true)

        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)


        def relation = UserRole.findByUserAndRole(testUser, userRole) ?: UserRole.create(testUser, adminRole, true).save()
    }
    def destroy = {
    }
}

package p.hh.figrails.domain

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class UserRole implements Serializable {

    private static final long serialVersionUID = 1

    User user
    Role role

    static UserRole get(long secUserId, long secRoleId) {
        criteriaFor(secUserId, secRoleId).get()
    }

    static boolean exists(long secUserId, long secRoleId) {
        criteriaFor(secUserId, secRoleId).count()
    }

    private static DetachedCriteria criteriaFor(long secUserId, long secRoleId) {
        UserRole.where {
            user == User.load(secUserId) &&
                    role == Role.load(secRoleId)
        }
    }

    static UserRole create(User secUser, Role secRole, boolean flush = false) {
        def instance = new UserRole(user: secUser, role: secRole)
        instance.save(flush: flush, insert: true)
        instance
    }

    static boolean remove(User u, Role r, boolean flush = false) {
        if (u == null || r == null) return false

        int rowCount = UserRole.where { user == u && role == r }.deleteAll()

        if (flush) { UserRole.withSession { it.flush() } }

        rowCount
    }

    static void removeAll(User u, boolean flush = false) {
        if (u == null) return

        UserRole.where { user == u }.deleteAll()

        if (flush) { UserRole.withSession { it.flush() } }
    }

    static void removeAll(Role r, boolean flush = false) {
        if (r == null) return

        UserRole.where { role == r }.deleteAll()

        if (flush) { UserRole.withSession { it.flush() } }
    }

    static constraints = {
        role validator: { Role r, UserRole ur ->
            if (ur.user == null || ur.user.id == null) return
            boolean existing = false
            UserRole.withNewSession {
                existing = UserRole.exists(ur.user.id, r.id)
            }
            if (existing) {
                return 'userRole.exists'
            }
        }
    }

    static mapping = {
        id composite: ['user', 'role']
        version false
    }

    @Override
    boolean equals(other) {
        if (!(other instanceof UserRole)) {
            return false
        }

        other.user?.id == user?.id && other.role?.id == role?.id
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (user) builder.append(user.id)
        if (role) builder.append(role.id)
        builder.toHashCode()
    }
}

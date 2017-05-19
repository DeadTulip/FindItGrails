package p.hh.figrails.domain

class User {

    transient springSecurityService

    String username
    String password

    Set<Team> teams = []

    static hasMany = [teams: Team]
    static belongsTo = Team
    static transients = ['springSecurityService']
    static mapping = {
        table 'user'
        username column: 'username'
        password column: 'password'
        teams joinTable: [name: 'team_user', key: 'user_id']
    }

    static constraints = {
        username blank: false, unique: true
        password blank: false
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this)*.role
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }


    boolean isEnabled() {
        return true
    }

    boolean isAccountExpired() {
        return false
    }

    boolean isAccountLocked() {
        return false
    }

    boolean isPasswordExpired() {
        return false
    }
}

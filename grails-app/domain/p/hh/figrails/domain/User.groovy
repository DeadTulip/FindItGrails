package p.hh.figrails.domain

class User {

    String userName
    Set<Team> teams = []

    static hasMany = [teams: Team]

    static belongsTo = Team

    static mapping = {
        table 'user'
        userName column: 'username'
        teams joinTable: [name: 'team_user', key: 'user_id']
    }

    static constraints = {
    }
}

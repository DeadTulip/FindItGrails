package p.hh.figrails.domain

class Team {

    String teamName
    Set<User> members = []

    static hasOne = [creator: User]
    static hasMany = [members: User]
    static belongsTo = [User]

    static mapping = {
        table 'team'
        teamName column: 'teamname'
        creator column: 'creator'
        members joinTable: [name: 'team_user', key: 'team_id']
    }

    static constraints = {
        teamName nullable: false
        creator nullable: false
    }
}

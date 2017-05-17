package p.hh.figrails.domain

class PhysicalLocation extends Location {

    String name
    String description

    static mapping = {
        table 'physical_location'
        name column: 'name'
        description column: 'description'
    }

    static constraints = {
        name nullable: false, maxSize: 200
        description maxSize: 500
    }
}

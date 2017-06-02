package p.hh.figrails.domain

class DiskLocation extends Location {

    String originalName
    String onDiskName

    static mapping = {
        table 'disk_location'
        onDiskName column: 'on_disk_name'
    }

    static constraints = {
        originalName nullable: false, maxSize: 200
        onDiskName nullable: false, maxSize: 200
    }
}

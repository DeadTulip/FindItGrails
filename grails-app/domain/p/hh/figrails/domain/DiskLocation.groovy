package p.hh.figrails.domain

class DiskLocation extends Location {

    String onDiskName

    static mapping = {
        table 'disk_location'
        onDiskName column: 'on_disk_name'
    }

    static constraints = {
        onDiskName nullable: false, maxSize: 200
    }
}

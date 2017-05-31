package p.hh.figrails.domain

class DiskItem extends Item {

    String fileType
    Long fileSize

    static mapping = {
        discriminator 'D'
        fileType column: 'file_type'
        fileSize column: 'file_size'
    }

    static constraints = {

    }
}

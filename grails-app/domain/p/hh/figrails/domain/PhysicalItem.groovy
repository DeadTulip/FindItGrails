package p.hh.figrails.domain

class PhysicalItem extends Item {

    DiskLocation picture

    static mapping = {
        discriminator 'P'
        picture column: 'picture'
    }

    static constraints = {
    }
}

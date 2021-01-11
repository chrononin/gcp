
resource "google_compute_disk" "default" {
  count   = var.counter
  name    = "${var.iname}-${var.instname}-disk-${count.index}"
  type    = "pd-ssd"
  zone    = "${var.zone}"
  image   = "${var.os["debian9"]}"
  size    = "${var.disk_size}"
  labels  = {
    environment = "dev"
  }
  #physical_block_size_bytes = 4096
}
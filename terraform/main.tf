
resource "google_compute_address" "static" {
  count   = var.counter
  name    = "ipv4-address-${count.index}"
}

resource "google_compute_instance" "vm-instance" {
  count        = var.counter
  name         = "${var.iname}-${var.instname}-${count.index}"
  machine_type = "${var.vm_type["standard"]}"
  zone         = "${var.zone}"

  tags = [
    "${var.prefix}-${var.network}-firewall-ssh",
    "${var.prefix}-${var.network}-firewall-http",
    "${var.prefix}-${var.network}-firewall-https"
  ]

#DISK ADDED HERE (using .name attribute instead of self_link yields the same result)
  boot_disk {
    source      = "${var.iname}-${var.instname}-disk-${count.index}" 
    device_name = "${var.iname}-${var.instname}-disk-${count.index}-boot" 
  }

  network_interface {
    subnetwork = "${google_compute_subnetwork.vpc_network_subnetwork.name}"

    # access_config {
    #   // Ephemeral IP
    # }
    access_config {
      nat_ip  = google_compute_address.static[count.index].address
    }
  }

}

output "vm_addresses" {
  value     = google_compute_address.static 
}
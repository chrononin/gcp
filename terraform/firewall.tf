resource "google_compute_firewall" "ssh" {
  name    = "${var.prefix}-${var.network}-firewall-ssh"
  network = "${google_compute_network.vpc_network.name}"

  allow {
    protocol = "tcp"
    ports    = ["22"]
  }

  target_tags   = ["${var.prefix}-${var.network}-firewall-ssh"]
  source_ranges = ["0.0.0.0/0"]
}

resource "google_compute_firewall" "http" {
  name    = "${var.prefix}-${var.network}-firewall-http"
  network = "${google_compute_network.vpc_network.name}"

  allow {
    protocol = "tcp"
    ports    = ["80"]
  }

  target_tags   = ["${var.prefix}-${var.network}-firewall-http"]
  source_ranges = ["0.0.0.0/0"]
}

resource "google_compute_firewall" "https" {
  name    = "${var.prefix}-${var.network}-firewall-https"
  network = "${google_compute_network.vpc_network.name}"

  allow {
    protocol = "tcp"
    ports    = ["443"]
  }

  target_tags   = ["${var.prefix}-${var.network}-firewall-https"]
  source_ranges = ["0.0.0.0/0"]
}

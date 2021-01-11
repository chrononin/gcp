resource "google_compute_network" "vpc_network" {
  name                            = "${var.prefix}-${var.network}"
  description                     = "Encapsulates resources for this project"
  auto_create_subnetworks         = false
  delete_default_routes_on_create = false
  
}

resource "google_compute_subnetwork" "vpc_network_subnetwork" {
  name          = "${var.prefix}-${var.network}-subnetwork-${var.subnetwork-region}"
  region        = "${var.subnetwork-region}"
  network       = "${google_compute_network.vpc_network.self_link}"
  ip_cidr_range = "192.168.10.0/8"
}

resource "google_compute_project_default_network_tier" "default" {
  network_tier = "PREMIUM"  # STANDARD, PREMIUM
}




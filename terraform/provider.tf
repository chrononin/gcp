provider "google" {
  version = "3.20.0"

  credentials = "auth/MyProject42bb59ccfe87.json"  #Map your credential file here
  project = var.project_name
  region  = var.region
  zone    = var.zone
}
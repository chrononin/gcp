
terraform {
  backend "gcs" {
    credentials = "auth/MyProject42bb59ccfe87.json" #map your gcp auth file here
    bucket  = "pd-terraform"  #update your terraform bucket for the backend here. 
//    prefix  = "010121" #prefix will be passed as argument to the init command, terraform init -backend-config "prefix=<forldername>"
  }
}


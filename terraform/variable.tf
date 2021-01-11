variable "prefix" {
  default     ="op-tst"
}
variable "iname" {
  type        = string
  default     = "iname"
  description = "This variable is used accrosed the projects to set the i(nfrasture)name"
}

variable "region" {
  default     = "us-central1" 
}
variable "zone" {
  default     =  "us-central1-a"
}   

variable "project_name" {
  default     = "boxwood-atom-288015"
}

variable "subnetwork-region" {
  default     = "us-central1"
}

variable "network" {
  default     = "vpc-network"
}

variable "vm_type" {
  default     ={
    micro     = "f1-micro"
    small     = "g1-small"
    standard  = "n1-standard-2"
  }
}

variable "os" {
  default     = {
    "debian9" = "debian-cloud/debian-9"
  }
}
variable "counter" {
  default     = 2
}

variable "instname" {
    default   =  "vmdeb"
} 
variable "disk_size" {
  default     = 10
}

variable "sqltype" {
  type        = string
  default     = "nosql"
  description = "provide the sql type: valid values are 1. 'nosql' 2. 'sql'"
}
variable backendfolder {
  type        = string
  default     = "010121"
  description = "This is the directory where backedn file will be stored"
}


resource "random_id" "mysql" {
  keepers = {
    # Generate a new id each time we switch to a new instance name
    iname_id = "${var.iname}"
  }

  byte_length = 2
}
resource "google_sql_database_instance" "master" {
  name             = "${var.iname}_mysql_${random_id.mysql.hex}"
  database_version = "MYSQL_5_7"  # Currently we are providing only one version MYSQL_5_7
  project          = var.project_name
  region           = var.region
  count            = "${var.sqltype == "sql" ? 1 : 0}"

  settings {
    # Second-generation instance tiers are based on the machine
    # type. 
    tier = "db-f1-micro"
  }
}

resource "google_sql_user" "users" {
  count    = "${var.sqltype == "sql" ? 1 : 0}"
  name     = "root"
  instance = google_sql_database_instance.master[count.index].name
  password = "changeme"
  
}
output mysql_db {
  value = {
    instance_details = google_sql_database_instance.master
    user_details     = google_sql_user.users
  }
    # connection_name = google_sql_database_instance.master[0].connection_name
    # master_instance_name = google_sql_database_instance.master[0].master_instance_name
    # instance_name = google_sql_database_instance.master[0].name
    # root_username = google_sql_user.users[0].name
    # root_password = google_sql_user.users[0].password
    # public_ip_address = google_sql_database_instance.master[0].public_ip_address
    # private_ip_address = google_sql_database_instance.master[0].private_ip_address
}


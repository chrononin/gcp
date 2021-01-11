resource "google_bigquery_dataset" "dataset" {
  dataset_id                  = "${var.iname}_dataset"
  friendly_name               = "${var.iname}_dataset"
  description                 = "This is a bigquery dataset description"
  location                    = "US"
  default_table_expiration_ms = 3600000
  delete_contents_on_destroy  = true
  count = "${var.sqltype == "nosql" ? 1 : 0}"

}

output nosql {
  value       = google_bigquery_dataset.dataset //.dataset_id
}


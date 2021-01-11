Inside this directory terraform related templates will be placed. 

terraform init -backend-config "prefix=<forldername>"  // this is reruired to specify the backend directory of the intrastructure
terraform plan -var "counter=1" -var "disk_size=20" -var "sqltype=nosql"
    /-var   counter 
    /-var   disk_size 
    /-var   sqlType 
    

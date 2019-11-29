## Note:
The `rds-combined-ca-bundle.pem` was downloaded from https://s3.amazonaws.com/rds-downloads/rds-combined-ca-bundle.pem
It was mentioned in https://docs.aws.amazon.com/documentdb/latest/developerguide/developerguide.pdf

## References

### Connection
https://docs.aws.amazon.com/documentdb/latest/developerguide/developerguide.pdf

Connect with Spring Boot:
https://gist.github.com/EliasRanz/758a1a884cf2eb3fea69309f9531c524

This is a guideline how to connect from outside VPC (Need an additional EC2) (for testing): 
https://docs.aws.amazon.com/en_us/documentdb/latest/developerguide/connect-from-outside-a-vpc.html
https://www.mongodbmanager.com/connecting-amazon-documentdb

Connect through a Proxy
https://stackoverflow.com/questions/55171021/aws-documentdb-through-proxy
Use ```mongo --ssl --sslAllowInvalidHostnames ...```

Connect via NodeJS:
https://medium.com/@cmani/get-going-with-amazon-documentdb-4f547bcbefc8 

### Index fields
https://www.baeldung.com/spring-data-mongodb-index-annotations-converter

### Mongo Events Listener
We use this for automatically generating createdDateTime & updatedDateTime.<br/>
The example code is implemented inside `pro-02-auditable-fields`
 * http://www.baeldung.com/cascading-with-dbref-and-lifecycle-events-in-spring-data-mongodb
 * https://www.baeldung.com/spring-boot-mongodb-auto-generated-field
 
### Run from local machine
In short, to connect to AWS DocumentDB from local machine, you need to do port forward from your local machine to DocumentDB cluster via an EC2 instance.
Note: 
- The EC2 instance and the AWS DocumentDB must have the same VPC configuration.
- And in the VPC configuration, you should open the Inbound port 22 so that you can ssh into EC2 instance and do the port forward.
   
So, you'll need:
- the host of DocumentDB Cluster
- the host of EC2 instance (public DNS)
- A pem file to ssh to EC2 instance

Then execute some command lines similar like this in your local machine:
``` 
DOCUMENTDB_HOST=docdb-2019-11-29-15-06-41.cluster-cn7ir1tmkuua.us-east-2.docdb.amazonaws.com
EC2_HOST=ec2-3-15-33-197.us-east-2.compute.amazonaws.com
EC2_PRIVATE_KEY_PATH="/home/kevintran/Documents/Environments/aws-new/sandbox/doc_db_test_ec2.pem"

ssh -i $EC2_PRIVATE_KEY_PATH -L 27017:$DOCUMENTDB_HOST:27017 ubuntu@$EC2_HOST -N
```
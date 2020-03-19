# AWS LAMBDA with ApiGateway, DynamoDB working with Quarkus

## build and deploy
* build: `./build.sh`
* deploy: `./deploy.sh`
* build-and-deploy: `./build-and-deploy.sh`

## use

* Get the endpoint from the output of the deploy or run `sls info`
* send the following payload:
    ```json
    {
        "message": "hello world",
        "author": "nick"
    }
    ```
 * tail logs: `serverless logs -f messageFunction -t`


 ## author
 [https://twitter.com/TheNickVanHoof](https://twitter.com/TheNickVanHoof)
 
 ## instructions
 * prune: `sls prune -n <number of version to keep> --dryRun`
 
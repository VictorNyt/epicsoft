keygen:
	openssl genrsa -out rsaPrivateKey.pem 2048
	openssl rsa -in rsaPrivateKey.pem -pubout -out publicKey.pem
	openssl pkcs8 -topk8 -nocrypt -inform PEM -in rsaPrivateKey.pem -out privateKey.pem

run:
	quarkus dev

build:
	quarkus build --native
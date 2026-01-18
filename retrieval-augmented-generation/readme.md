 
# Retrival Augmented Generation

Retriveal augmented generation (RAG) allows for the models to import data at runtime.  The following are examples of using Java to create RAG systems.

## Markus Eisele

[Build Agent-Ready RAG Systems in Java with Quarkus and Docling](https://www.the-main-thread.com/p/enterprise-rag-quarkus-docling-pgvector-tutorial)

This article shows how to use Dev Services in Quarkius to configure a Postgres SQL database

```
# Database URL for dev profile (often auto-configured by Dev Services if left out)
%dev.quarkus.datasource.url=jdbc:postgresql://localhost:5432/devdb
%dev.quarkus.datasource.username=devuser
%dev.quarkus.datasource.password=devsecret
```

[Building a Secure Hybrid RAG Pipeline in Java with Quarkus and JWT](https://www.the-main-thread.com/p/secure-enterprise-rag-quarkus-jwt-tutorial) - This article builds on the previous RAG one, adding JSON Web Tokens and users and roles for authentication.


## [Up](../readme.md)

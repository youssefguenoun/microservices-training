#!/usr/bin/env bash
docker run -p 52002:52002 -e PORT='52002' -e STOCK_URL='http://localhost:52003' yguenoun/sales-service:0.0.1-SNAPSHOT
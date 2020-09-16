
ALTER TABLE "covid_case" ADD COLUMN "last_updated" TIMESTAMP;
ALTER TABLE "covid_case" ALTER COLUMN "last_updated" SET DEFAULT NOW();


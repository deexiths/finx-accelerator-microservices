import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { finx_test_executionsService } from './services/finx_test_executionsService';
import { finx_test_executionsController } from './controllers/finx_test_executionsController';
import { Finx_test_executions } from './models/finx_test_executions';
import { finx_test_executionsRepository } from './repositories/finx_test_executionsRepository';
import { Finx_test_plane } from './models/finx_test_plane';
import { Finx_test_suites } from './models/finx_test_suites';
import config from '../config.json';
@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'postgres',
      host: config.DB_HOST,
      port: config.DB_PORT,
      username: config.DB_USERNAME,
      password: config.DB_PASSWORD,
      database: config.DB_DATABASE,
      entities: [Finx_test_executions, Finx_test_plane, Finx_test_suites],
      synchronize: true,
      ssl: {
        rejectUnauthorized: false,
      },
      extra: {
        max: 5,
        idleTimeoutMillis: 30000,
        connectionTimeoutMillis: 30000
      }
    }),
    TypeOrmModule.forFeature([Finx_test_executions, Finx_test_plane, Finx_test_suites]),
  ],
  controllers: [finx_test_executionsController,],
  providers: [finx_test_executionsService, finx_test_executionsRepository,],
})
export class AppModule { }
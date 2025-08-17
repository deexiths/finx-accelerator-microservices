// CRUD
import { Controller, Post, Get, Body,Param, Put, Delete ,Query} from '@nestjs/common';
import { finx_test_executionsService } from '../services/finx_test_executionsService';

@Controller('/finx-test-executions')
export class finx_test_executionsController {

    constructor(private readonly finx_test_executionsService: finx_test_executionsService) {}

    @Post()
    async createFinx_test_executions(@Body() finx_test_executionsData: any) {
      return this.finx_test_executionsService.createFinx_test_executions(finx_test_executionsData);
    }

    @Get()
    async getFinx_test_executions(@Query('id') id?: string) {
      return this.finx_test_executionsService.getFinx_test_executions(id);
    }

    @Get(':id')
    async getFinx_test_executionsById(@Param('id') id: string) {
      return this.finx_test_executionsService.getFinx_test_executionsById(id);
    }

    @Put(':id')
    async updateFinx_test_executions(@Param('id') id: string,@Body() updatedData: any) {
      return this.finx_test_executionsService.updateFinx_test_executions(id,updatedData);
    }

    @Delete(':id')
    async deleteFinx_test_executions(@Param('id') id: string) {
      return this.finx_test_executionsService.deleteFinx_test_executions(id);
    }
}


# Tasks: 従業員一覧取得

**Input**: Design documents from `specs/feature/001-list-employees/`
**Prerequisites**: `plan.md`, `spec.md`, `data-model.md`, `contracts/get-employees-endpoint.md`

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Project initialization and base Spring Boot structure for the employee list API.

- [ ] T001 Create Gradle build files and dependency configuration in `build.gradle`, `settings.gradle`, and `gradle.properties`
- [ ] T002 Create Spring Boot application entry point in `src/main/java/com/example/employeeapi/EmployeeApiApplication.java`
- [ ] T003 [P] Add H2 persistence and Spring Boot test dependencies to `build.gradle`
- [ ] T004 [P] Create application configuration in `src/main/resources/application.yml`
- [ ] T005 [P] Create initial integration test bootstrap in `src/test/java/com/example/employeeapi/EmployeeApiApplicationTests.java`

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Core backend components required before implementing any user story.

- [ ] T006 [P] Create `Employee` entity model in `src/main/java/com/example/employeeapi/model/Employee.java`
- [ ] T007 [P] Create `EmployeeRepository` interface in `src/main/java/com/example/employeeapi/repository/EmployeeRepository.java`
- [ ] T008 [P] Create `EmployeeService` implementation in `src/main/java/com/example/employeeapi/service/EmployeeService.java`
- [ ] T009 Integrate with existing authorization infrastructure to validate allowed roles in `src/main/java/com/example/employeeapi/controller/EmployeeController.java`
- [ ] T010 Create API error DTO in `src/main/java/com/example/employeeapi/exception/ApiError.java`
- [ ] T011 Create global exception handler in `src/main/java/com/example/employeeapi/exception/GlobalExceptionHandler.java`
- [ ] T012 Create `EmployeeController` skeleton in `src/main/java/com/example/employeeapi/controller/EmployeeController.java`

---

## Phase 3: User Story 1 - 従業員一覧を取得する (Priority: P1) 🎯 MVP

**Goal**: 権限のあるアクターが従業員番号、氏名、所属部署、役職、在籍ステータスを含む従業員一覧を取得できること。

**Independent Test**: `GET /api/employees` を呼び出し、従業員番号順にソートされた従業員リストが返ることを確認する。

### Tests for User Story 1

- [ ] T013 [P] [US1] Create contract test for successful employee list retrieval in `src/test/java/com/example/employeeapi/contract/EmployeeControllerContractTest.java`
- [ ] T014 [P] [US1] Create unit test for `EmployeeService` list retrieval and sorting in `src/test/java/com/example/employeeapi/service/EmployeeServiceTest.java`

### Implementation for User Story 1

- [ ] T015 [US1] Create employee list response DTO in `src/main/java/com/example/employeeapi/dto/EmployeeListResponse.java`
- [ ] T016 [US1] Implement `GET /api/employees` endpoint in `src/main/java/com/example/employeeapi/controller/EmployeeController.java`
- [ ] T017 [US1] Implement employee list retrieval and dictionary-order sorting in `src/main/java/com/example/employeeapi/service/EmployeeService.java`
- [ ] T018 [US1] Add response mapping from `Employee` entity to API JSON in `src/main/java/com/example/employeeapi/controller/EmployeeController.java`

---

## Phase 4: User Story 2 - 従業員が存在しない場合の対応 (Priority: P2)

**Goal**: 従業員が存在しない場合に空状態メッセージを含む明確なレスポンスを返すこと。

**Independent Test**: データベースに従業員が1件も存在しない状態で `GET /api/employees` を実行し、空配列と空状態メッセージを受け取る。

### Tests for User Story 2

- [ ] T019 [P] [US2] Create contract test for empty employee list response in `src/test/java/com/example/employeeapi/contract/EmployeeControllerEmptyStateContractTest.java`
- [ ] T020 [P] [US2] Create unit test for empty result handling in `src/test/java/com/example/employeeapi/controller/EmployeeControllerEmptyStateTest.java`

### Implementation for User Story 2

- [ ] T021 [US2] Extend `EmployeeListResponse` with optional empty-state message in `src/main/java/com/example/employeeapi/dto/EmployeeListResponse.java`
- [ ] T022 [US2] Update `EmployeeController` to return `message` when the employee list is empty in `src/main/java/com/example/employeeapi/controller/EmployeeController.java`
- [ ] T023 [US2] Update service or controller logic to preserve empty list semantics in `src/main/java/com/example/employeeapi/service/EmployeeService.java`

---

## Phase 5: User Story 3 - 権限のないアクセスを拒否する (Priority: P3)

**Goal**: 権限のないアクターが従業員一覧を要求したときに `403 Forbidden` を返すこと。

**Independent Test**: 権限のないユーザーとして `GET /api/employees` を実行し、明確な権限エラーメッセージが返ることを確認する。

### Tests for User Story 3

- [ ] T024 [P] [US3] Create contract test for unauthorized access in `src/test/java/com/example/employeeapi/contract/EmployeeControllerUnauthorizedContractTest.java`
- [ ] T025 [P] [US3] Create unit test for role validation in `src/test/java/com/example/employeeapi/security/AuthorizationServiceTest.java`

### Implementation for User Story 3

- [ ] T026 [US3] Implement role validation for allowed roles `administrator` and `hr` using existing authorization context in `src/main/java/com/example/employeeapi/controller/EmployeeController.java`
- [ ] T027 [US3] Integrate authorization check into `EmployeeController` in `src/main/java/com/example/employeeapi/controller/EmployeeController.java`
- [ ] T028 [US3] Return `403 Forbidden` with `ApiError` payload from `GlobalExceptionHandler` in `src/main/java/com/example/employeeapi/exception/GlobalExceptionHandler.java`

---

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Documentation, API contract finalization, and project-level validation.

- [ ] T029 [P] Update OpenAPI documentation in `docs/architecture/api/get-employees-openapi.yaml`
- [ ] T030 Update quickstart documentation in `specs/feature/001-list-employees/quickstart.md`
- [ ] T031 [P] Add or refine Spring Boot test coverage in `src/test/java/com/example/employeeapi/`
- [ ] T032 Update `build.gradle` if needed after test execution

---

## Dependencies & Execution Order

### Phase Dependencies

- **Phase 1: Setup** must complete before Phase 2 begins.
- **Phase 2: Foundational** must complete before any user story work begins.
- **Phase 3+: User Stories** may proceed in parallel after foundational tasks complete.
- **Phase 6: Polish** depends on all completed user stories.

### User Story Dependencies

- **US1** depends on the foundational backend model, repository, and controller skeleton.
- **US2** depends on US1 for endpoint behavior and adds empty-state response handling.
- **US3** depends on foundational authorization support and integrates authorization checks with the same endpoint.

### Parallel Opportunities

- Foundational model/repository/service implementation tasks are parallelizable across separate files.
- Contract and unit tests for each story can be created in parallel when different files are targeted.
- Documentation updates and OpenAPI refinement can be parallelized with implementation cleanup.

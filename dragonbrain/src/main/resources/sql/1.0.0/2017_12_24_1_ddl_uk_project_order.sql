ALTER TABLE project
  ADD CONSTRAINT ok_project_order UNIQUE (order_by);

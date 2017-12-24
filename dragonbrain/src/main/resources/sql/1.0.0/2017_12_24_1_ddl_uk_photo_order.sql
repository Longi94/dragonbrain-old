ALTER TABLE photo
  ADD CONSTRAINT uk_photo_order UNIQUE (order_by);

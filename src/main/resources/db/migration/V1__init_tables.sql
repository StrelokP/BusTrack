CREATE TABLE `role` (
  id BIGINT NOT NULL,
   name VARCHAR(255) NULL,
   CONSTRAINT pk_role PRIMARY KEY (id)
);

ALTER TABLE `role` ADD CONSTRAINT uc_role_name UNIQUE (name);

CREATE TABLE users (
  id BIGINT NOT NULL,
   login VARCHAR(255) NULL,
   password VARCHAR(255) NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_roles (
  user_id BIGINT NOT NULL,
   roles_id BIGINT NOT NULL,
   CONSTRAINT pk_users_roles PRIMARY KEY (user_id, roles_id)
);

ALTER TABLE users_roles ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE users_roles ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);

CREATE TABLE transport_type (
  id BIGINT NOT NULL,
   name VARCHAR(255) NULL,
   CONSTRAINT pk_transport_type PRIMARY KEY (id)
);

CREATE TABLE transport (
  id BIGINT NOT NULL,
   type_id BIGINT NULL,
   CONSTRAINT pk_transport PRIMARY KEY (id)
);

ALTER TABLE transport ADD CONSTRAINT FK_TRANSPORT_ON_TYPE FOREIGN KEY (type_id) REFERENCES transport_type (id);

CREATE TABLE station (
  id BIGINT NOT NULL,
   name VARCHAR(255) NULL,
   lat DOUBLE NULL,
   lng DOUBLE NULL,
   CONSTRAINT pk_station PRIMARY KEY (id)
);

CREATE TABLE routes (
  id BIGINT NOT NULL,
   num VARCHAR(255) NULL,
   color VARCHAR(255) NULL,
   CONSTRAINT pk_routes PRIMARY KEY (id)
);

CREATE TABLE routes_stations (
  route_id BIGINT NOT NULL,
   stations_id BIGINT NOT NULL,
   CONSTRAINT pk_routes_stations PRIMARY KEY (route_id, stations_id)
);

CREATE TABLE routes_transports (
  route_id BIGINT NOT NULL,
   transports_id BIGINT NOT NULL,
   CONSTRAINT pk_routes_transports PRIMARY KEY (route_id, transports_id)
);

ALTER TABLE routes_stations ADD CONSTRAINT fk_rousta_on_route FOREIGN KEY (route_id) REFERENCES routes (id);

ALTER TABLE routes_stations ADD CONSTRAINT fk_rousta_on_station FOREIGN KEY (stations_id) REFERENCES station (id);

ALTER TABLE routes_transports ADD CONSTRAINT fk_routra_on_route FOREIGN KEY (route_id) REFERENCES routes (id);

ALTER TABLE routes_transports ADD CONSTRAINT fk_routra_on_transport FOREIGN KEY (transports_id) REFERENCES transport (id);
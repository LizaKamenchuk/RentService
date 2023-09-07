alter table transmission alter column transmission_type SET not null;
alter table transmission add unique(transmission_type) ;
alter table fuel alter column fuel_type SET not null;
alter table fuel add unique(fuel_type) ;
alter table class alter column class_type SET not null;
alter table class add unique(class_type) ;
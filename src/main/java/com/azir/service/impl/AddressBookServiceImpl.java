package com.azir.service.impl;

import com.azir.entity.AddressBook;
import com.azir.mapper.AddressBookMapper;
import com.azir.service.AddressBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}

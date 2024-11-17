module jmp.app {
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;
    requires jmp.dto;

    requires jmp.service.api;
    requires jmp.bank.api;
    requires jmp.dto;

    uses com.epam.jmp.service.Service;
    uses com.epam.jmp.service.Bank;
}